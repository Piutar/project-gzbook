import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StatusLikerListComponent } from './status-liker-list.component';

describe('StatusLikerListComponent', () => {
  let component: StatusLikerListComponent;
  let fixture: ComponentFixture<StatusLikerListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StatusLikerListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StatusLikerListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
