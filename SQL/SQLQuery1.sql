create trigger triGradeDelete on tb_basicMessage
for delete
 as 
  declare @id varchar(10)
  select @id = id from deleted
  delete from tb_contact where tb_contact.id = @id
