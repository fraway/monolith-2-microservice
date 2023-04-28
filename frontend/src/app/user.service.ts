import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from './models';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private client: HttpClient) { }

  create(user: Partial<User>) {
    return this.client.post(environment.monolithicEndpoint, user)
  }

  list() {
    return this.client.get<User[]>(environment.monolithicEndpoint)
  }
}
