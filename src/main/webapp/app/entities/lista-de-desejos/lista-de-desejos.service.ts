import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IListaDeDesejos } from 'app/shared/model/lista-de-desejos.model';

type EntityResponseType = HttpResponse<IListaDeDesejos>;
type EntityArrayResponseType = HttpResponse<IListaDeDesejos[]>;

@Injectable({ providedIn: 'root' })
export class ListaDeDesejosService {
    private resourceUrl = SERVER_API_URL + 'api/lista-de-desejos';

    constructor(private http: HttpClient) {}

    create(listaDeDesejos: IListaDeDesejos): Observable<EntityResponseType> {
        return this.http.post<IListaDeDesejos>(this.resourceUrl, listaDeDesejos, { observe: 'response' });
    }

    update(listaDeDesejos: IListaDeDesejos): Observable<EntityResponseType> {
        return this.http.put<IListaDeDesejos>(this.resourceUrl, listaDeDesejos, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<IListaDeDesejos>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IListaDeDesejos[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
