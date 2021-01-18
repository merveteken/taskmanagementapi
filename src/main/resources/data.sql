INSERT INTO public.role (name) VALUES ('TASKER');
INSERT INTO public.role (name) VALUES ('CUSTOMER');
INSERT INTO public.role (name) VALUES ('ADMIN');

INSERT INTO public.task
(customer_id, "date", description, price, tasker_id)
VALUES(0, '2021-01-15', 'Dog Training', 0, 0);

INSERT INTO public.task
(customer_id, "date", description, price, tasker_id)
VALUES(0, '2021-02-01', 'Personel Training', 0, 0);

INSERT INTO public.task
(customer_id, "date", description, price, tasker_id)
VALUES(0, '2021-03-14', 'Home Clainig', 0, 0);

INSERT INTO public.task
(customer_id, "date", description, price, tasker_id)
VALUES(0, '2021-06-07', 'Task 2', 0, 0);

INSERT INTO public.task
(customer_id, "date", description, price, tasker_id)
VALUES(0, '2021-01-10', 'Another Task', 0, 0);