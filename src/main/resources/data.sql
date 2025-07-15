-- ========= 1. ROLES (seguridad y lógica de negocio) =========
INSERT INTO roles (id,nombre) VALUES
  (1,'CLIENTE'),
  (2,'ADMIN');

-- ========= 2. USUARIOS (login)  =========
-- password = "admin123" en BCrypt
INSERT INTO usuario (id,username,password,enabled) VALUES
  (1,'admin','$2a$10$yR5bHQ6uq7jr3P8c1WSfpOAxrvB9nIWqUnXzU/3XvpANklfVQ7TSa',true);

  /* simon / 1234       → rol CLIENTE (id 1) */
INSERT INTO usuario (id, username, password, enabled)
VALUES (2, 'simon', '$2a$10$Kb7e3R/2mOvYRhNth4Ft0eUnGna5uzy8Ukpfzv7gU6uLzAmUMd1mi', true);

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES
  (1, 2),   -- admin  → ADMIN
  (2, 1);   -- simon  → CLIENTE

-- ========= 3. CLIENTES y su rol lógico =========
INSERT INTO clientes (id,nombre,correo,contraseña,puntos_fidelidad) VALUES
  (1,'Simón Riffo','simoncito@gmail.com','$2a$10$hash...',0),
  (2,'Laura Pérez','laurita@gmail.com','$2a$10$hash...',120);

INSERT INTO cliente_roles (cliente_id,role_id) VALUES
  (1,1), (2,1);                   -- ambos son CLIENTE

-- ========= 4. CATEGORÍAS, PROVEEDORES, PRODUCTOS =========
INSERT INTO categoria (id,nombre) VALUES
  (1,'LIBROS'),
  (2,'PAPELERÍA'),
  (3,'REGALOS');

INSERT INTO proveedor (id,nombre,email,telefono) VALUES
  (1,'ACME Books','ventas@acme.cl','+56 9 1234 5678'),
  (2,'Papeles S.A.','contacto@papeles.cl','+56 2 2345 6789');

INSERT INTO producto (id,nombre,precio,categoria_id,proveedor_id) VALUES
  (1,'El Principito',12000,1,1),
  (2,'Cuaderno A5',2500,2,2),
  (3,'Lámina Origami',600,2,2);

-- ========= 5. SUCURSALES e INVENTARIO =========
INSERT INTO sucursal (id,nombre,direccion,region) VALUES
  (1,'Sucursal Centro','Av. Matta 123, Santiago','RM'),
  (2,'Sucursal Conce','Tucapel 300, Concepción','Biobio');

INSERT INTO inventario (id,producto_id,sucursal_id,stock) VALUES
  (1,1,1,15),
  (2,1,2,10),
  (3,2,1,40),
  (4,3,1,100);

-- ========= 6. PEDIDOS + DETALLE =========
INSERT INTO pedido (id,cliente_id,fecha,estado) VALUES
  (1,1,CURRENT_DATE(),'PENDIENTE');

INSERT INTO pedidos_productos (pedido_id,producto_id) VALUES
  (1,1), (1,2);       -- pedido 1 lleva libro y cuaderno

-- ========= 7. RESERVAS =========
INSERT INTO reserva (id,cliente_id,producto_id,sucursal_id,fecha_retiro,estado) VALUES
  (1,1,3,1,CURRENT_DATE() + 3,'PENDIENTE');

-- ========= 8. NOTIFICACIONES DE EJEMPLO =========
INSERT INTO notificacion (id,cliente_id,fecha,mensaje,leida) VALUES
  (1,1,CURRENT_TIMESTAMP(),'Tu reserva #1 está lista!',false);