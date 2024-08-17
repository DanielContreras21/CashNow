package s1014ftjavaangular.loansapplication.domain.mapper;

import org.springframework.stereotype.Component;
import s1014ftjavaangular.loansapplication.domain.model.dto.request.GeneralDataDto;
import s1014ftjavaangular.loansapplication.domain.model.entity.GeneralData;

import java.util.function.Function;

@Component
public class GeneralDataMapper {

    public final Function<GeneralDataDto, GeneralData> dtoToModel = (dto) -> {
        GeneralData entity = new GeneralData();
        entity.setLoanApplicationId(dto.getLoanApplicationId());
        entity.setIdentification(dto.getIdentification());
        entity.setIdentificationType(dto.getIdentificationType());
        entity.setName(dto.getName());
        entity.setLastname(dto.getLastname());
        entity.setGenre(dto.getGenre());
        entity.setBirthdate(dto.getBirthdate());
        entity.setNationality(dto.getNationality());
        entity.setCity(dto.getCity());
        entity.setState(dto.getState());
        entity.setAddress(dto.getAddress());
        entity.setApartment(dto.getApartment());
        entity.setZipcode(dto.getZipcode());
        entity.setPhone(dto.getPhone());

        return entity;
    };
}
