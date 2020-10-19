import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentLikerListComponent } from './comment-liker-list.component';

describe('CommentLikerListComponent', () => {
  let component: CommentLikerListComponent;
  let fixture: ComponentFixture<CommentLikerListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommentLikerListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommentLikerListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
