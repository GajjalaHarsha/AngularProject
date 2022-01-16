import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminupdatedoctorComponent } from './adminupdatedoctor.component';

describe('AdminupdatedoctorComponent', () => {
  let component: AdminupdatedoctorComponent;
  let fixture: ComponentFixture<AdminupdatedoctorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminupdatedoctorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminupdatedoctorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
