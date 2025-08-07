@echo off


REM === DESTINATION PARAMETERS ===
set destinationserver=.
set destinationpassword=sql@s3rv3r
set destinationuser=sa
set destinationdatabase=ZABPOSDB

REM === Resolve current directory of this script ===
setlocal enabledelayedexpansion
set scriptdir=%~dp0
set cleanupfile=%scriptdir%cleanup.sql

echo Running cleanup.sql on remote server %destinationserver%...
sqlcmd -S %destinationserver% -U %destinationuser% -P %destinationpassword% -d %destinationdatabase% -i "%cleanupfile%"
echo Done.

endlocal
pause

