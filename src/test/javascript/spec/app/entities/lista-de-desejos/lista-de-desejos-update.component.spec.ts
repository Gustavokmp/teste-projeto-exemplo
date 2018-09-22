/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterTestModule } from '../../../test.module';
import { ListaDeDesejosUpdateComponent } from 'app/entities/lista-de-desejos/lista-de-desejos-update.component';
import { ListaDeDesejosService } from 'app/entities/lista-de-desejos/lista-de-desejos.service';
import { ListaDeDesejos } from 'app/shared/model/lista-de-desejos.model';

describe('Component Tests', () => {
    describe('ListaDeDesejos Management Update Component', () => {
        let comp: ListaDeDesejosUpdateComponent;
        let fixture: ComponentFixture<ListaDeDesejosUpdateComponent>;
        let service: ListaDeDesejosService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [ListaDeDesejosUpdateComponent]
            })
                .overrideTemplate(ListaDeDesejosUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ListaDeDesejosUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ListaDeDesejosService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ListaDeDesejos('123');
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.listaDeDesejos = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ListaDeDesejos();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.listaDeDesejos = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
