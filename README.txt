JSON to XML converter.

Reads the JSON content from the given input file and parses it to XML with respective attributes and write it to the given output file.

-------------------------------------------------------------------------------------------------------
Dependencies used:

org.json.jar - for JSON parsing requirements.
download link - http://www.java2s.com/Code/JarDownload/org.json/org.json.jar.zip

junit - for unit test cases.
download link - https://download.jar-download.com/cache_jars/junit/junit/4.12/jar_files.zip

-------------------------------------------------------------------------------------------------------
Build solution:

Java 7 or later should be present in the system.
Download and extract Ant from https://ant.apache.org/bindownload.cgi (compatible version).
Set ANT_HOME system variable to ant_home and PATH environemnt variable to ant_home\bin.
Open command prompt in this location and run the command.

ant

csw.jar will be produced in dist folder bundled with the dependency jar.

-------------------------------------------------------------------------------------------------------
Run solution:

Open command prompt where csw.jar is present and run the following command.

java -jar csw.jar <input_json_file_path> <output_xml_file_path>

-------------------------------------------------------------------------------------------------------