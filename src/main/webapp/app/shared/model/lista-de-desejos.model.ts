import { IProduto } from 'app/shared/model//produto.model';
import { ICliente } from 'app/shared/model//cliente.model';

export interface IListaDeDesejos {
    id?: string;
    title?: string;
    restricted?: boolean;
    produtos?: IProduto[];
    cliente?: ICliente;
}

export class ListaDeDesejos implements IListaDeDesejos {
    constructor(
        public id?: string,
        public title?: string,
        public restricted?: boolean,
        public produtos?: IProduto[],
        public cliente?: ICliente
    ) {
        this.restricted = this.restricted || false;
    }
}
