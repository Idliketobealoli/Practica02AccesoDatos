package filterClasses;

import model.City;
import model.Measurement;
import org.jfree.chart.ChartUtilities;
import utils.Util;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CreateHTML {
    public CreateHTML(City city, String path) {
        Path processedPath = processPath(city, Path.of(path));
        Path pathToImages = createImageFolder(Path.of(path));
        generateHTML(city, processedPath, pathToImages);
    }

    /**
     * Este método crea la carpeta resources dentro del directorio donde le indiques.
     * @author Daniel Rodríguez Muñoz
     * @param path
     * @return
     */
    private Path createImageFolder(Path path) {
        return Paths.get(path + File.separator + "resources");
    }

    /**
     * A partir de un objeto City y un Path, returnea un Path con el path al directorio que le hayas pasado y
     * dentro un archivo llamado "[nombre_City]-dd-MM-yyyy.html", donde dd-MM-yyyy es la fecha en la que se llamó
     * a este método.
     * @author Daniel Rodríguez Muñoz
     * @param city City
     * @param path Path
     * @return Path
     * @see City
     * @see Path
     */
    private Path processPath(City city, Path path) {
        String dateNow = Util.giveMeDateNow();
        return Paths.get(path + File.separator + city.getNameCity() + "-" + dateNow + ".html");
    }

    /**
     * Este método generará un html a partir de un Path y una City dados.
     * Si la ruta ya existe, pedirá por consola si quieres sobreescribir el archivo (borrar el anterior y
     * guardar este en la ruta dada). De recibir una respuesta afirmativa, lo hará, a no ser que no pueda borrar
     * dicho archivo, en cuyo caso lo notificará por consola y acabará la ejecución sin modificar ningún archivo.
     * En el caso de recibir una respuesta negativa, terminará sin modificar nada.
     * @author Daniel Rodríguez Muñoz
     * @param city City
     * @param path Path
     * @see Path
     * @see City
     * @see Scanner
     */
    private void generateHTML(City city, Path path, Path pathToImages) {
        File html = path.toFile();
        if (!html.exists()) {
            File resources = pathToImages.toFile();
            if (!resources.exists()) {
                resources.mkdirs();
            }
            generateResourcesFolder(city, pathToImages);
            try (FileWriter writer = new FileWriter(html)) {
                writer.write(writeHTML(city, pathToImages));
                Desktop.getDesktop().browse(path.toUri());
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.println("'" + html.getName() + "' already exists, would you like to overwrite the file? [y/n]");
            String answer = "";
            while (!(answer.equalsIgnoreCase("y")) &&
                    !(answer.equalsIgnoreCase("n"))) {
                answer = sc.next();
            }
            if (answer.equalsIgnoreCase("y")) {
                boolean result_of_deleting = html.delete();
                if (!result_of_deleting) {
                    System.out.println("Unable to delete "+ html.getName());
                    System.exit(99_999);
                } else {
                    generateHTML(city, path, pathToImages);
                }
            } else {
                System.exit(88_888);
            }
        }
    }

    /**
     * Este método crea una carpeta recursos y guarda cada chart en dicha carpeta, en formato png.
     * @author Daniel Rodríguez Muñoz
     * @param city City
     * @param pathToImages Path
     */
    private void generateResourcesFolder(City city, Path pathToImages) {
        for (Measurement meas : city.getMeteoMeasurementsCity()) {
            setUpChartsFolder(pathToImages, meas);
        }
        for (Measurement meas : city.getContaminationMeasurementsCity()) {
            setUpChartsFolder(pathToImages, meas);
        }
    }

    /**
     * Me gustaría decir que este método funciona correctamente, pero sólo crea las carpetas donde irían los png's, sin
     * crear los susodichos.
     * @author Daniel Rodríguez Muñoz
     * @param pathToImages
     * @param meas
     */
    private void setUpChartsFolder(Path pathToImages, Measurement meas) {
        Path path = Paths.get(pathToImages + File.separator + meas.getMagnitudeMeasurement());
        File file = path.toFile();
        String pathImage = path + File.separator + meas.getMagnitudeMeasurement() + ".png";
        File fileImage = new File(pathImage);
        if (fileImage.exists()){
            fileImage.delete();
        }
        if (file.exists()){
            file.delete();
        }
        boolean mkdirsDidItsJob = false;
        try {
            if (!(file.exists())){
                mkdirsDidItsJob = file.mkdirs();
            }
            if (mkdirsDidItsJob) {
                ChartUtilities.saveChartAsPNG(new File(pathImage),
                        meas.getChart(), 450, 400);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Este método returneará un String con el html generado a partir del objeto City pasado por parámetro.
     * @autor Daniel Rodríguez Muñoz
     * @param city City
     * @return String
     * @see City
     */
    private String writeHTML(City city, Path pathToImages) {
        StringBuilder sb = new StringBuilder();
        sb.append(""); // esto está aquí para prevenir que returnee nulos, aunque sea redundante.
        String firstMeasDate = formateameDate(city.getFirstMeasurementDateCity());
        String lastMeasDate = formateameDate(city.getLastMeasurementDateCity());
        sb.append("<!doctype html> \n" +
                "<html lang=\"es\"> \n" +
                "\t<head> \n" +
                "\t\t<meta charset=\"utf-8\"/> \n" +
                "\t\t<title>El tiempo en " + city.getNameCity() + "</title> \n" +
                "\t</head> \n" +
                "\t<body> \n" +
                "\t\t<h1>Estadísticas de <i>" + city.getNameCity() + "</i>:</h1> \n" +
                "\t\t<p> \n\t\t\tPrimera medición registrada: <i>" + firstMeasDate + "</i><br/> \n" +
                "\t\t\tÚltima medición registrada: <i>" + lastMeasDate + "</i><br/> \n" +
                "\t\t</p> \n" +
                "\t\t<p> \n" +
                "\t\t\t<b>Estaciones asociadas:</b><br/> \n" +
                "\t\t\t<ul> \n");
        for (String station: city.getAssociatedStationListCity()) {
            sb.append("\t\t\t\t<li>" + station + "</li> \n");
        }
        sb.append("\t\t\t</ul> \n" +
                "\t\t</p> \n" +
                "\t\t<p> \n" +
                "\t\t\t<b>Información meteorológica:</b><br/> \n" +
                "\t\t\t<ul> \n");
        for (Measurement measure: city.getMeteoMeasurementsCity()) {
            if (measure.getMagnitudeMeasurement() != 89) {
                sb.append("\t\t\t\t<li><i>" + measure.getMagnitudeNameMeasurement() + "</i> \n" +
                        "\t\t\t\t\t<ul> \n" +
                        "\t\t\t\t\t\t<li>" + measure.getMagnitudeNameMeasurement() + " media mensual: " +
                        measure.getAverageValueMeasurement() + measure.getMeasurementUnitName() + "</li> \n" +
                        "\t\t\t\t\t\t<li>Máxima registrada: " + measure.getMaxValueMeasurement() +
                        measure.getMeasurementUnitName() + " - " + formateameDate(measure.getMomentMaxValueMeasurement()) + "</li> \n" +
                        "\t\t\t\t\t\t<li>Mínima registrada: " + measure.getMinValueMeasurement() +
                        measure.getMeasurementUnitName() + " - " + formateameDate(measure.getMomentMinValueMeasurement()) + "</li> \n" +
                        "\t\t\t\t\t\t<li>Evolución mensual: <br/> \n" +
                        "\t\t\t\t\t\t\t<img src=\"" + pathToImages + File.separator + measure.getMagnitudeMeasurement() + File.separator + measure.getMagnitudeMeasurement() + ".png\" name=\"" +
                        measure.getMagnitudeNameMeasurement() + "\" id=\"" + measure.getMagnitudeMeasurement() + "\"/> \n" +
                        "\t\t\t\t\t\t</li> \n" +
                        "\t\t\t\t\t</ul> \n" +
                        "\t\t\t\t</li>");
            } else {
                sb.append("\t\t\t\t<li><i>" + measure.getMagnitudeNameMeasurement() + "</i> \n" +
                        "\t\t\t\t\t<ul> \n" +
                        "\t\t\t\t\t\t<li>" + measure.getMagnitudeNameMeasurement() + " media mensual: " +
                        measure.getAverageValueMeasurement() + measure.getMeasurementUnitName() + "</li> \n" +
                        "\t\t\t\t\t\t<li>Lista de días en los que ha llovido: \n" +
                        "\t\t\t\t\t\t\t<ul> \n");
                if (measure.getDaysOnWhichRained().isEmpty()) {
                    sb.append("\t\t\t\t\t\t\t\t<li>No llovió ningún día.</li> \n");
                } else {
                    for (int i = 0; i < measure.getDaysOnWhichRained().size(); i++) {
                        sb.append("\t\t\t\t\t\t\t\t<li>" + formateameDateSinHour(measure.getDaysOnWhichRained().get(i)) +"- " +
                                "Precipitación: " + measure.getRainMeasurements().get(i) +
                                measure.getMeasurementUnitName() + "</li> \n");
                    }
                }
                sb.append("\t\t\t\t\t\t\t</ul> \n" +
                        "\t\t\t\t\t\t</li>" +
                        "\t\t\t\t\t\t<li>Histograma: <br/> \n" +
                        "\t\t\t\t\t\t\t<img src=\"" + pathToImages + File.separator + measure.getMagnitudeMeasurement() + File.separator + measure.getMagnitudeMeasurement() + ".png\" name=\"" +
                        measure.getMagnitudeNameMeasurement() + "\" id=\"" + measure.getMagnitudeMeasurement() + "\"/> \n" +
                        "\t\t\t\t\t\t</li> \n" +
                        "\t\t\t\t\t</ul> \n" +
                        "\t\t\t\t</li>");
            }
        }
        sb.append("\t\t\t</ul> \n" +
                "\t\t</p> \n" +
                "\t\t<p> \n" +
                "\t\t\t<b>Información sobre contaminación:</b><br/> \n" +
                "\t\t\t<ul> \n");
        for (Measurement measure: city.getContaminationMeasurementsCity()) {
            sb.append("\t\t\t\t<li><i>" + measure.getMagnitudeNameMeasurement() + "</i> \n" +
                    "\t\t\t\t\t<ul> \n" +
                    "\t\t\t\t\t\t<li>Valor medio mensual: " +
                    measure.getAverageValueMeasurement() + measure.getMeasurementUnitName() + "</li> \n" +
                    "\t\t\t\t\t\t<li>Máxima registrada: " + measure.getMaxValueMeasurement() +
                    measure.getMeasurementUnitName() + " - " + formateameDate(measure.getMomentMaxValueMeasurement()) + "</li> \n" +
                    "\t\t\t\t\t\t<li>Mínima registrada: " + measure.getMinValueMeasurement() +
                    measure.getMeasurementUnitName() + " - " + formateameDate(measure.getMomentMinValueMeasurement()) + "</li> \n" +
                    "\t\t\t\t\t\t<li>Evolución mensual: <br/> \n" +
                    "\t\t\t\t\t\t\t<img src=\"" + pathToImages + File.separator + measure.getMagnitudeMeasurement() + File.separator + measure.getMagnitudeMeasurement() + ".png\" name=\"" +
                    measure.getMagnitudeNameMeasurement() + "\" id=\"" + measure.getMagnitudeMeasurement() + "\"/> \n" +
                    "\t\t\t\t\t\t</li> \n" +
                    "\t\t\t\t\t</ul> \n" +
                    "\t\t\t\t</li>");
        }
        sb.append("\t\t\t</ul> \n" +
                "\t\t</p> \n" +
                "\t\t<p> \n" +
                "\t\t\t<b>Hecho por:</b> <br/> \n" +
                "\t\t\t<ul> \n" +
                "\t\t\t\t<li><i>Daniel Rodríguez Muñoz (loli)</i></li> \n" +
                "\t\t\t\t<li><i>Jaime Salcedo Vallejo</i></li> \n" +
                "\t\t\t</ul> \n" +
                "\t\t</p> \n" +
                "\t</body> \n" +
                "</html>");
        return sb.toString();
    }

    /**
     * Este método formatea el objeto Date pasado por parámetro al patrón "dd/MM/yyyy - HH:mm:ss",
     * devolviéndolo como String.
     * @author Daniel Rodríguez Muñoz
     * @param date Date
     * @return String
     */
    private String formateameDate(Date date) {
        String formattedDate = date.toGMTString();
        formattedDate = formattedDate.replace("GMT", "");
        return formattedDate;
    }

    /**
     * Este método formatea el objeto Date pasado por parámetro al patrón "dd/MM/yyyy",
     * devolviéndolo como String.
     * @author Daniel Rodríguez Muñoz
     * @param date Date
     * @return String
     */
    private String formateameDateSinHour(Date date) {
        String dateWithoutHour = date.toGMTString();
        dateWithoutHour = Pattern.compile("[0-9]{2}:[0-9]{2}:[0-9]{2} GMT$").matcher(dateWithoutHour).replaceAll("");
        return dateWithoutHour;
    }
}
