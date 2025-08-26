import { IdentificationType } from "./identification-type";

export interface GuarantorRequest {
  loanApplicationId: string;
  name: string;
  lastname: string;
  identificationType: IdentificationType;
  identification: string;
  city: string;
  state: string;
  address: string;
  apartment: string;
  zipcode: string;
  phone: string;
  company: string;
  businessCategory: string;
  occupation: string;
  timeInCompany: number;
  monthlyIncome: number;
  companyCity: string;
  companyState: string;
  companyAddress: string;
  companyApartment: string;
  companyZipcode: string;
  companyPhone: string;
}
