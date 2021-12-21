package bank.accenture.accenture.bank.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import bank.accenture.accenture.bank.DTO.AgencyDTO;
import bank.accenture.accenture.bank.domain.Agency;

@Mapper(componentModel = "spring")
public abstract class AgencyMapper {
	public static final AgencyMapper INSTANCE  = Mappers.getMapper(AgencyMapper.class);
	
	public abstract Agency toAgency(AgencyDTO agencyDTO);
}
