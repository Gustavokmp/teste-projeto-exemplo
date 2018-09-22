import { ICliente } from 'app/shared/model//cliente.model';

export interface IEndereco {
    id?: string;
    rua?: string;
    numero?: number;
    cidade?: string;
    estado?: string;
    postcode?: string;
    cliente?: ICliente;
}

export class Endereco implements IEndereco {
    constructor(
        public id?: string,
        public rua?: string,
        public numero?: number,
        public cidade?: string,
        public estado?: string,
        public postcode?: string,
        public cliente?: ICliente
    ) {}
}
