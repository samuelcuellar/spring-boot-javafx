insert into menuitem(id, parent, key, value, service, grid_def, expanded)
values(0, -1, 'master', 'System', null, null, 1);

insert into menuitem(id, parent, key, value, service, grid_def, expanded, image)
values(1, 0, 'master.customers', 'Customers', 'customerService', 'customerGridDef', 0, 'customers_16x16.png');

insert into menuitem(id, parent, key, value, service, grid_def, expanded, image)
values(2, 0, 'master.categories', 'Categories', 'categoryService', 'categoryGridDef', 0, 'category_16x16.png');

insert into menuitem(id, parent, key, value, service, grid_def, expanded, image)
values(3, 0, 'master.products', 'Products', 'productService', 'productGridDef', 0, 'product_16x16.png');

insert into menuitem(id, parent, key, value, service, grid_def, expanded, image)
values(4, 0, 'master.invoices', 'Invoices', 'invoiceService', 'invoiceGridDef', 0, 'invoice_16x16.png');

