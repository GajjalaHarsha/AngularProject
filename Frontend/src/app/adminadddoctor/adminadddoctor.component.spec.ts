import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminadddoctorComponent } from './adminadddoctor.component';

describe('AdminadddoctorComponent', () => {
  let component: AdminadddoctorComponent;
  let fixture: ComponentFixture<AdminadddoctorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminadddoctorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminadddoctorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
