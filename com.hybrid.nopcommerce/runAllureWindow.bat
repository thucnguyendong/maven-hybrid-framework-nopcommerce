set ProjectPath=%~dp0
cd %ProjectPath%
echo %ProjectPath% 
set p=%PATH%
java -javaagent:"%ProjectPath%lib\AllureReport\aspectjweaver-1.8.10.jar" -classpath "%ProjectPath%bin;%ProjectPath%lib\AllureReport\*;%ProjectPath%lib\Extent Report V3\*;%ProjectPath%lib\log4JLib\*;%ProjectPath%lib\*;%ProjectPath%lib\reportNGLib\*;%ProjectPath%lib\webDriverManagerLib\*" org.testng.TestNG "%ProjectPath%bin\runNopCommerce.xml"
pause