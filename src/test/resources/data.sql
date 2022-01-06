INSERT INTO `roles` (`id_role`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

INSERT INTO `localization` (`id_localization`, `city`) VALUES
(1, 'Tarnow'),
(2, 'Krakow'),
(3, 'Rzeszow'),
(4, 'Nowy Targ');

INSERT INTO `car` (`id_car`, `color`, `engine_capacity`, `image`, `mark`, `model`, `money`, `type`, `year_production`, `id_localization`) VALUES
(1, 'czarny', 1200, 'https://image.ceneostatic.pl/data/products/66661051/i-toyota-yaris-ii-2008-87km-hatchback-czarny.jpg', 'Toyota', 'Yaris', 79, 'hatchback', 2018, 1);