CREATE VIEW auction_view as
select Au.id as appuser_id, Ur.role_id as appuser_role, Ra.type as asset_type, Fl.id as asset_id, Ad.city as city, Ad.postcode as postcode, Ad.street as street, Ad.homenumber as homenumber, Ad.localnumber as localnumber, Fl.price as price, Fl.size as size, Fl.rooms as rooms, Fl.floor as floor, null  as plot_type, cast(null as int) as house_on_plot
FROM APPUSER Au, FLAT Fl, ADRESS Ad, REAL_ASSETS Ra, USER_ROLES Ur
WHERE Fl.appuser_id = Au.id
and Fl.adress_id = Ad.id
and Ad.realassetsid = Ra.id
and Ur.appuser_id = Au.id
union
select Au.id, Ur.role_id, Ra.type, Ho.id, Ad.city, Ad.postcode, Ad.street, Ad.homenumber, Ad.localnumber, Ho.price, Ho.size, Ho.rooms, null, null, null
FROM APPUSER Au, HOUSE Ho, ADRESS Ad, REAL_ASSETS Ra, USER_ROLES Ur
WHERE Ho.appuser_id = Au.id
and Ho.adress_id = Ad.id
and Ad.realassetsid = Ra.id
and Ur.appuser_id = Au.id
union
select Au.id, Ur.role_id, Ra.type, Pl.id, Ad.city, Ad.postcode, Ad.street, Ad.homenumber, Ad.localnumber, Pl.price, Pl.size, null, null, Pl.type, Pl.house_id
FROM APPUSER Au, PLOT Pl, ADRESS Ad, REAL_ASSETS Ra, USER_ROLES Ur
WHERE Pl.appuser_id = Au.id
and Pl.adress_id = Ad.id
and Ad.realassetsid = Ra.id
and Ur.appuser_id = Au.id

