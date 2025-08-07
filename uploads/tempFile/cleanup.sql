USE ZABPOSDB;
GO

DELETE FROM oppossigninlog
WHERE xdate < DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()) - 2, 0)
DELETE FROM opdodetail
WHERE xdate < DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()) - 2, 0)
DELETE FROM opdoheader
WHERE xdate < DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()) - 2, 0)
DELETE FROM opposmultiplecard
WHERE ztime < DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE())-2,0)


-- Truncate the log by changing the database recovery model to SIMPLE.
ALTER DATABASE ZABPOSDB
SET RECOVERY SIMPLE;
GO
-- Shrink the truncated log file to 1 MB.
DBCC SHRINKFILE (ZABPOSDB_log, 2);
GO
-- Reset the database recovery model.
ALTER DATABASE ZABPOSDB
SET RECOVERY FULL;
GO




