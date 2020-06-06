/*
1) Вывести все уникальные имена ролей пользователей
*/
select distinct rr.role_name as "Роль"
from public.m_roles rr
/*
2) Подсчитать число машин у каждого пользователя.
Вывести в формате User full name (username + пробел + user surname) | Число машин у пользователя.
*/
select distinct concat(uu.username, ' ', uu.surname, ' | ', count(cc.id)) as "Пользователь|Кол-во авто"
from public.m_users uu,
     public.m_cars cc
where cc.user_id = uu.id
group by uu.username, uu.surname
/*
3) Подсчитать для каждого диллера число машин, старше 2018 года производства с красным кузовом.
*/
select dd.name, count(cc.id)
from m_auto_dealer dd,
     m_cars cc,
     m_body bb
where dd.id = cc.dealer_id
  and bb.car_id = cc.id
  and cc."dateCar" >= '2018-01-01'
  and bb.color = 'Red'
group by dd.name
/*
4) Найти пользователей не из Беларуси и России, у которых есть машина 2010-2015 года выпуска из Германии и
купленную в диллере не в Германии с объемом двигателя больше 3 литра.
*/
select distinct uu.id         as "ID USER",
                uu.username   as "Пользователь",
                ll.country    as "Страна пользователя",
                cc.id,
                cc.model      as "Модель авто",
                ll2.country   as "Страна выпуска авто",
                engine.id,
                engine.volume as "Объем двигателя",
                dealer.id     as "ID DEALER",
                dealer.name   as "Дилер",
                ll3.country   as "Страна дилера"
from m_users uu,
     l_user_location lu,
     m_location ll,
     m_cars cc,
     l_car_location cl,
     m_location ll2,
     m_engine engine,
     m_auto_dealer dealer,
     l_dealer_location ldealer,
     m_location ll3
where uu.id = lu."idUser"
  and lu."idLocation" = ll.id
  and (ll.country <> 'Belarus' and ll.country <> 'Russia')/*Определяем локацию пользователей*/
  and uu.id = cc.user_id
  and cl."idCar" = cc.id
  and ll2.id = cl."idLocation"
  and ll2.country = 'Germany' /*Определяем локацию(где выпущено) авто*/
  and engine.car_id = cc.id
  and engine.volume > 3 /*Определяем объем двигателя*/
  and dealer.id = ldealer."idDealer"
  and ldealer."idLocation" = ll3.id
  and cc.dealer_id = dealer.id
  and ll3.country <> 'Germany'
/*Определяем локацию дилера авто*/
/*
5) Определить логины пользователей, имеющих больше 3 машин.
*/
select uu.login
from public.m_users uu,
     public.m_cars cc
where cc.user_id = uu.id
group by uu.login
having count(cc.id) > 2
/*6) Вывести уникальных диллеров с подсчитанной суммой стоимостей машин,
  связанных с ними.*/
select dealer.id as "ID DEALER", dealer.name as "ДИЛЕР", sum(cars.price) as "Общая стоимость машин у дилера"
from m_auto_dealer dealer,
     m_cars cars
where dealer.id = cars.dealer_id
group by dealer.id, dealer.name
/*
7) Подсчитать количество уникальных пользователей, владеющих хотя бы одной машиной, стоимость которой превышает среднюю стоимость всех машин.
*/
select count(vv.username) as "Кол-во уникальных пользователей"
from (select distinct us.id, us.username
      from m_users us,
           m_cars cc
      where us.id = cc.user_id
        and cc.price > (select avg(cars.price)
                        from m_cars cars)) vv