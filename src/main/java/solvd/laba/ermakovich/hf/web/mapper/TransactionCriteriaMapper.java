package solvd.laba.ermakovich.hf.web.mapper;

import org.mapstruct.Mapper;
import solvd.laba.ermakovich.hf.domain.TransactionSearchCriteria;
import solvd.laba.ermakovich.hf.web.dto.TransactionSearchCriteriaDto;

/**
 * @author Ermakovich Kseniya
 */
@Mapper(componentModel = "spring")
public interface TransactionCriteriaMapper {

    TransactionSearchCriteria toEntity(TransactionSearchCriteriaDto criteriaDto);

}
