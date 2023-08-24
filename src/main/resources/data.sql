insert into public.postal_office
(id, index, name)
values
(
    1,
    '420048',
    'Severniy'
) ON CONFLICT DO NOTHING;

insert into public.postal_office
(id, index, name)
values
(
    2,
    '420100',
    'Azino-1'
) ON CONFLICT DO NOTHING;

insert into public.postal_office
(id, index, name)
values
(
    3,
    '420101',
    'Azino-2'
) ON CONFLICT DO NOTHING;

insert into public.postal_office
(id, index, name)
values
(
    4,
    '420036',
    'Kvartala-1'
) ON CONFLICT DO NOTHING;

insert into public.postal_office
(id, index, name)
values
(
    5,
    '420111',
    'Moskovskiy-3'
) ON CONFLICT DO NOTHING;