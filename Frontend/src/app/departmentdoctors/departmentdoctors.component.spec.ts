import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepartmentdoctorsComponent } from './departmentdoctors.component';

describe('DepartmentdoctorsComponent', () => {
  let component: DepartmentdoctorsComponent;
  let fixture: ComponentFixture<DepartmentdoctorsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DepartmentdoctorsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DepartmentdoctorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
