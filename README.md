#PRÁCTICA 2 ACCESO A DATOS.
##Hecho por:
- Daniel Rodríguez.
- Jaime Salcedo.


##Explicación:
- Lee los CSV's y los carga en listas de objetos.
- Con esas listas de objetos, las mete en un xml para cada lista mediante jDom.
- Esos xml los lee con jDom y los carga de nuevo en listas de objetos.
- Con el parámetro ciudad que le habremos introducido como argumento (si no tiene
ciudad y un directorio donde guardarlo, 
el programa finaliza sin soltar excepción al usuario, pidiéndole que introduzca 
el número correcto de argumentos y diciéndole qué argumentos se traga y en qué 
orden) filtramos las listas de objetos por dicha ciudad, sólo si existe en 
nuestro mapa de ciudades (de lo contrario le dice al usuario que no tiene datos para 
esa ciudad y el programa finaliza).
- Una vez filtrado por ciudad, usamos la API Stream para procesar las listas y conseguir 
un objeto City con una lista de mediciones meteorológicas y de contaminación.
- Con ese objeto city, generamos un html en la ruta 
especificada por el usuario mediante el segundo argumento.
- Si dicha ruta está ocupada, pide al usuario confirmación 
sobre si quiere o no sobreescribir dicho archivo.
- En caso negativo, el programa finaliza.
- En caso afirmativo, borra el archivo anterior y el método se llama a sí mismo mediante 
recursividad para crearse, pues ya esa uri está libre.
- Tras crearse el html, automáticamente es mostrado en el navegador por defecto.
- Después de esto, el objeto city es almacenado en un objeto Result, que contiene 
dicho objeto City, un identificador único UUID y una fecha de generación.
- Dicho objeto result es almacenado en la base de datos de resultados (mediciones.xml) 
únicamente si no hay otro objeto resultado con la misma ciudad y dia de generación que él.
- Si la base de datos no existe, la crea y le mete el objeto Resultado generado en esta ejecución del programa.
- Tras guardarlo (o no) en la base de datos, el programa crea un documento 
markdown mediante el uso de XPath en el cual muestra la media de cada una de las mediciones de esa ciudad.
- Si hay múltiples objetos Resultado para la misma ciudad, dará las medias de cada Measurement para cada objeto Result 
cuya ciudad asociada sea la especificada.
- A la vez que crea el informe markdown, crea también un informe por el terminal.
- Tras acabar el informe, abre el editor de texto o navegador por defecto y muestra el informe.


#LINK REPOSITORIO GITHUB:
https://github.com/Idliketobealoli/Practica02AccesoDatos
https://github.com/jaimesalcedo1/Practica02AccesoDatos

#LINK A VIDEO GOOGLE DRIVE (no dejaba subirlo a yt):
https://drive.google.com/file/d/1ZPpPdsdJ0afpaB-VaehLTK0lMyHxD11g/view?usp=sharing