-- VIEW UNTUK SUBTOTAL DI ORDERED PRODUCT
go
create view subtotal as
select order_id, product_id, harga_product * kuantitas as subtotal
from ordered_product
go

-- VIEW TOTAL DI ORDER PRODUCT
create view total as
select op.order_id, coalesce(st.total, 0) as total 
from (
    select order_id, sum(subtotal) as total
    from subtotal
    group by order_id
) as st
left outer join
order_product as op 
on st.order_id=op.order_id
go

-- VIEW UMUR
create view umur as
select id_pelanggan, datediff(year, tanggal_lahir, getdate()) as umur
from customer_profile