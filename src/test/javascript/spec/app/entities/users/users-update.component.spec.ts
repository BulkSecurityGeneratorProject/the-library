/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { TheLibraryTestModule } from '../../../test.module';
import { UsersUpdateComponent } from 'app/entities/users/users-update.component';
import { UsersService } from 'app/entities/users/users.service';
import { Users } from 'app/shared/model/users.model';

describe('Component Tests', () => {
    describe('Users Management Update Component', () => {
        let comp: UsersUpdateComponent;
        let fixture: ComponentFixture<UsersUpdateComponent>;
        let service: UsersService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [TheLibraryTestModule],
                declarations: [UsersUpdateComponent]
            })
                .overrideTemplate(UsersUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(UsersUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UsersService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Users(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.users = entity;
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
                    const entity = new Users();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.users = entity;
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
