import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PdfService {

  constructor(private client: HttpClient) { }

  generate(req: { userID: number; bio: string; }) {
    return this.client.post<string>(environment.pdfEndpoint, {
      userId: req.userID,
      bio: req.bio
    })
  }
}
