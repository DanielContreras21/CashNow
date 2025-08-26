import { IdentificationType } from "./identification-type";

export interface GeneralDataRequest {
  loanApplicationId: string;
  customersUuid: string;
  identification: string;
  identificationType: IdentificationType;
  name: string;
  lastname: string;
  genre: string;
  birthdate: Date;
  nationality: string;
  requestedAmount: number;
  city: string;
  state: string;
  address: string;
  apartment: string;
  zipcode: string;
  phone: string;

}
