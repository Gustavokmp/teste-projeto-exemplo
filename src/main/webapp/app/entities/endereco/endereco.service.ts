import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEndereco } from 'app/shared/model/endereco.model';

type EntityResponseType = HttpResponse<IEndereco>;
type EntityArrayResponseType = HttpResponse<IEndereco[]>;

@Injectable({ providedIn: 'root' })
export class EnderecoService {
    private resourceUrl = SERVER_API_URL + 'api/enderecos';

    constructor(private http: HttpClient) {}

    create(endereco: IEndereco): Observable<EntityResponseType> {
        return this.http.post<IEndereco>(this.resourceUrl, endereco, { observe: 'response' });
    }

    update(endereco: IEndereco): Observable<EntityResponseType> {
        return this.http.put<IEndereco>(this.resourceUrl, endereco, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<IEndereco>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEndereco[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
