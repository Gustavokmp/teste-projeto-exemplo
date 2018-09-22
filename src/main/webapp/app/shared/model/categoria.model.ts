import { ICategoria } from 'app/shared/model//categoria.model';
import { IProduto } from 'app/shared/model//produto.model';

export const enum StatusDaCategoria {
    DISPONIVEL = 'DISPONIVEL',
    RESTRITO = 'RESTRITO',
    DESATIVADO = 'DESATIVADO'
}

export interface ICategoria {
    id?: string;
    descricao?: string;
    classificacao?: number;
    status?: StatusDaCategoria;
    parent?: ICategoria;
    produtos?: IProduto[];
}

export class Categoria implements ICategoria {
    constructor(
        public id?: string,
        public descricao?: string,
        public classificacao?: number,
        public status?: StatusDaCategoria,
        public parent?: ICategoria,
        public produtos?: IProduto[]
    ) {}
}
