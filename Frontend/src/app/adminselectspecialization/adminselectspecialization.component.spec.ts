import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminselectspecializationComponent } from './adminselectspecialization.component';

describe('AdminselectspecializationComponent', () => {
  let component: AdminselectspecializationComponent;
  let fixture: ComponentFixture<AdminselectspecializationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminselectspecializationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminselectspecializationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
