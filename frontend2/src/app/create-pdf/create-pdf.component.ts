import { Component, inject } from '@angular/core';
import { UserService } from '../user.service';
import { PdfService } from '../pdf.service';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-create-pdf',
  templateUrl: './create-pdf.component.html',
  styleUrls: ['./create-pdf.component.scss']
})
export class CreatePdfComponent {
  form = inject(FormBuilder).group({
    userID: inject(FormBuilder).control(-1),
    bio: inject(FormBuilder).control('')
  })

  _user = inject(UserService);
  _pdf = inject(PdfService);

  posting = false;

  users$ = this._user.list();

  fileName: string | undefined;

  submit() {
    const value = this.form.getRawValue()
    const userID = value.userID;
    const bio = value.bio!;

    this.posting = true;

    this._pdf.generate({
      userID: userID!,
      bio: bio!
    }).subscribe((fileName) => {

      this.posting = false;
      this.fileName = fileName;
    });
  }

}
