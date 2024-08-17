package s1014ftjavaangular.loansapplication.domain.mapper;

import org.springframework.stereotype.Component;
import s1014ftjavaangular.loansapplication.domain.model.dto.request.JobInformationDto;
import s1014ftjavaangular.loansapplication.domain.model.entity.JobInformation;

import java.util.function.Function;

@Component
public class JobInformationMapper {

    public static final Function<JobInformationDto, JobInformation> dtoToModel = (model) -> {

        JobInformation entity = new JobInformation();
        entity.setLoanApplicationId(model.getLoanApplicationId());
        entity.setCompany(model.getCompany());
        entity.setBusinessCategory(model.getBusinessCategory());
        entity.setOccupation(model.getOccupation());
        entity.setYearsInCompany(model.getYearsInCompany());
        entity.setMonthlyIncome(model.getMonthlyIncome());
        entity.setCity(model.getCity());
        entity.setState(model.getState());
        entity.setAddress(model.getAddress());
        entity.setApartment(model.getApartment());
        entity.setZipcode(model.getZipcode());
        entity.setPhone(model.getPhone());

        return entity;
    };


    private final Function<JobInformation, JobInformationDto> entityToModel = (entity) -> new JobInformationDto(
            entity.getLoanApplicationId(),
            entity.getCompany(),
            entity.getBusinessCategory(),
            entity.getOccupation(),
            entity.getYearsInCompany(),
            entity.getMonthlyIncome(),
            entity.getCity(),
            entity.getState(),
            entity.getAddress(),
            entity.getApartment(),
            entity.getZipcode(),
            entity.getPhone()

    );
}
