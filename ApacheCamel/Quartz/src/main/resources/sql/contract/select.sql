SELECT id
FROM model.dbo.contract
WHERE GETDATE() > expiration AND automaticRenovation = 1;