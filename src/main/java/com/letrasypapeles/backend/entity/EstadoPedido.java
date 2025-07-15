package com.letrasypapeles.backend.entity;

/**
 * Posibles estados que puede tener un pedido.
 * Ajusta o amplía los valores si lo necesitas.
 */
public enum EstadoPedido {
    PENDIENTE,
    EN_PROCESO,
    ENVIADO,
    ENTREGADO,
    CANCELADO
}