import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Person} from "../model/person";

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  private serviceUrl: string;

  constructor(private http: HttpClient) {
    this.serviceUrl = 'http://localhost:8080/api/persons';
  }

  public findAll(): Observable<Person[]> {
    return this.http.get<Person[]>(this.serviceUrl);
  }

  public save(person: Person) {
    return this.http.put<Person>(this.serviceUrl, person);
  }

  public delete(person: Person) {
    return this.http.delete<Person>(this.serviceUrl + "/" + person.id);
  }

  public update(user: Person) {
    return this.http.post<Person>(this.serviceUrl, user);
  }
}
