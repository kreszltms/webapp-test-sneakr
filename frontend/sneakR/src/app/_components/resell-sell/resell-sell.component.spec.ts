import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResellSellComponent } from './resell-sell.component';

describe('ResellSellComponent', () => {
  let component: ResellSellComponent;
  let fixture: ComponentFixture<ResellSellComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ResellSellComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ResellSellComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
