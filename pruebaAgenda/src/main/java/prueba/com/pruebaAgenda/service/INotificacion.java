package prueba.com.pruebaAgenda.service;

import prueba.com.pruebaAgenda.dto.NotificationRequestDto;

public interface INotificacion {

	void enviar(NotificationRequestDto notificacion);
}
