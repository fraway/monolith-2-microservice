import { Component, inject } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { UserService } from '../user.service';
import { User } from '../models';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.scss']
})
export class CreateUserComponent {
  form = inject(FormBuilder).group({
    username: [''],
    email: [''],
    address: ['']
  })

  _user = inject(UserService)

  submit() {
    const value = this.form.getRawValue();

    const u: Partial<User> = {
      username: value.username!,
      email: value.email!,
      address: value.address!
    }

    this._user.create(u).subscribe(() => {
      this.form.reset();
    });
  }
}
