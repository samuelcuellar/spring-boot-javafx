insert into menuitem(id, parent, key, value, service, grid_def, expanded)
values(0, -1, 'master', 'System', null, null, 1);

insert into menuitem(id, parent, key, value, service, grid_def, expanded, image)
values(1, 0, 'master.customers', 'Customers', 'customerService', 'customerGridDef', 0, 'customers_16x16.png');

insert into menuitem(id, parent, key, value, service, grid_def, expanded, image)
values(2, 0, 'master.categories', 'Categories', null, null, 0, 'category_16x16.png');

insert into menuitem(id, parent, key, value, service, grid_def, expanded, image)
values(3, 0, 'master.products', 'Products', null, null, 0, 'product_16x16.png');

insert into menuitem(id, parent, key, value, service, grid_def, expanded, image)
values(4, 0, 'master.invoices', 'Invoices', null, null, 0, 'invoice_16x16.png');


insert into customer(id, firstname, lastname, address, email)
values(0, 'John ', 'Doe', 'Happiness street 1234', 'john.doe@skynet.com');
