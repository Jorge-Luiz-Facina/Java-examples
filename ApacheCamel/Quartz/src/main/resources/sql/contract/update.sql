UPDATE  model.dbo.contract
SET expiration =  DATEADD(year, 1, GETDATE())
WHERE id = :#${body.get('id')};