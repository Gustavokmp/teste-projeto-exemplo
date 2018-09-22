import { IEndereco } from 'app/shared/model//endereco.model';
import { IListaDeDesejos } from 'app/shared/model//lista-de-desejos.model';

export interface ICliente {
    id?: string;
    firstName?: string;
    lastName?: string;
    email?: string;
    telephone?: string;
    enderecos?: IEndereco[];
    listaDeDesejos?: IListaDeDesejos[];
}

export class Cliente implements ICliente {
    constructor(
        public id?: string,
        public firstName?: string,
        public lastName?: string,
        public email?: string,
        public telephone?: string,
        public enderecos?: IEndereco[],
        public listaDeDesejos?: IListaDeDesejos[]
    ) {}
}
