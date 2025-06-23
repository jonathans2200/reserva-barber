-- ======= Clientes =======
INSERT INTO cliente (id, nombre, telefono)
VALUES (1, 'Juan Pérez', '0991234567');
INSERT INTO cliente (id, nombre, telefono)
VALUES (2, 'María Gómez', '0987654321');
INSERT INTO cliente (id, nombre, telefono)
VALUES (3, 'Luis Torres', '0961122334');
INSERT INTO cliente (id, nombre, telefono)
VALUES (4, 'Ana Ruiz', '0959988776');
INSERT INTO cliente (id, nombre, telefono)
VALUES (5, 'Carlos León', '0933445566');

-- ======= Barberos =======
INSERT INTO barbero (id, nombre, dia_descanso)
VALUES (1, 'Carlos Romero', 'MONDAY');
INSERT INTO barbero (id, nombre, dia_descanso)
VALUES (2, 'Luis Fernández', 'TUESDAY');
INSERT INTO barbero (id, nombre, dia_descanso)
VALUES (3, 'Andrés Gutiérrez', 'WEDNESDAY');

-- ======= Servicios =======
INSERT INTO servicio (id, nombre, precio, duracion_minutos)
VALUES (1, 'Corte Básico', 5.00, 30);
INSERT INTO servicio (id, nombre, precio, duracion_minutos)
VALUES (2, 'Corte + Barba', 8.00, 45);
INSERT INTO servicio (id, nombre, precio, duracion_minutos)
VALUES (3, 'Barba', 4.00, 20);
INSERT INTO servicio (id, nombre, precio, duracion_minutos)
VALUES (4, 'Mascarilla Facial', 6.00, 25);
INSERT INTO servicio (id, nombre, precio, duracion_minutos)
VALUES (5, 'Coloración', 10.00, 60);

-- ======= Reservas =======
INSERT INTO reserva (id, cliente_id, barbero_id, fecha, hora, hora_fin, total)
VALUES (1, 1, 1, '2025-06-24', '10:00:00', '10:30:00', 5.00);

INSERT INTO reserva (id, cliente_id, barbero_id, fecha, hora, hora_fin, total)
VALUES (2, 2, 2, '2025-06-24', '11:00:00', '11:45:00', 8.00);

INSERT INTO reserva (id, cliente_id, barbero_id, fecha, hora, hora_fin, total)
VALUES (3, 3, 3, '2025-06-24', '12:00:00', '12:20:00', 4.00);

INSERT INTO reserva (id, cliente_id, barbero_id, fecha, hora, hora_fin, total)
VALUES (4, 4, 1, '2025-06-24', '13:00:00', '13:25:00', 6.00);

INSERT INTO reserva (id, cliente_id, barbero_id, fecha, hora, hora_fin, total)
VALUES (5, 5, 2, '2025-06-24', '14:00:00', '15:00:00', 10.00);

-- ======= Detalle Reservas =======
INSERT INTO detalle_reserva (id, reserva_id, servicio_id)
VALUES (1, 1, 1);
INSERT INTO detalle_reserva (id, reserva_id, servicio_id)
VALUES (2, 2, 2);
INSERT INTO detalle_reserva (id, reserva_id, servicio_id)
VALUES (3, 3, 3);
INSERT INTO detalle_reserva (id, reserva_id, servicio_id)
VALUES (4, 4, 4);
INSERT INTO detalle_reserva (id, reserva_id, servicio_id)
VALUES (5, 5, 5);
