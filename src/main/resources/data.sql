INSERT INTO public.category (name, description)
SELECT 'Electronics', 'Devices and gadgets'
WHERE NOT EXISTS (SELECT 1 FROM public.category WHERE name = 'Electronics');

INSERT INTO public.category (name, description)
SELECT 'Furniture', 'Home and office furniture'
WHERE NOT EXISTS (SELECT 1 FROM public.category WHERE name = 'Furniture');