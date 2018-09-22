import { IListaDeDesejos } from 'app/shared/model//lista-de-desejos.model';
import { ICategoria } from 'app/shared/model//categoria.model';

export interface IProduto {
    id?: string;
    title?: string;
    palavraChave?: string;
    descricao?: string;
    avaliacao?: number;
    listaDeDesejos?: IListaDeDesejos;
    categorias?: ICategoria[];
}

export class Produto implements IProduto {
    constructor(
        public id?: string,
        public title?: string,
        public palavraChave?: string,
        public descricao?: string,
        public avaliacao?: number,
        public listaDeDesejos?: IListaDeDesejos,
        public categorias?: ICategoria[]
    ) {}
}
