import {Component, EventEmitter, Input, Output} from '@angular/core';
import {RacenetFilterForm} from "./racenet-filter.form";

@Component({
  selector: 'app-racenet-filter-form',
  templateUrl: './racenet-filter-form.component.html',
  styleUrls: ['./racenet-filter-form.component.scss']
})
export class RacenetFilterFormComponent {

  @Input("formGroup")
  racenetFilterFormGroup!: RacenetFilterForm;

  @Input()
  enableName = false;
  @Input()
  enableModeChange = true;

  @Input()
  public canPromote = false;

  @Input()
  public canDemote = false;

  @Output()
  public promote = new EventEmitter();

  @Output()
  public demote = new EventEmitter();

  updatePlayers() {
    if(this.racenetFilterFormGroup.filterMode.value == 'NONE') {
      this.racenetFilterFormGroup.racenetNames.clear();
    }
  }
  showPlayers() {
    return ["INCLUDE","EXCLUDE"].indexOf(this.racenetFilterFormGroup.filterMode.value) !== -1;
  }
}
