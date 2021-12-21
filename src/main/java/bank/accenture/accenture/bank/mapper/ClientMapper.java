package bank.accenture.accenture.bank.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import bank.accenture.accenture.bank.DTO.ClientDTO;
import bank.accenture.accenture.bank.domain.Client;

@Mapper(componentModel = "spring")
public abstract class ClientMapper {
	public static final ClientMapper INSTANCE  = Mappers.getMapper(ClientMapper.class);
	
	public abstract Client toClient(ClientDTO obj);
}
