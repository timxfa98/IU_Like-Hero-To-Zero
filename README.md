# Like Hero To Zero - CO2 Emissions Web Application

Eine Webanwendung zur Darstellung und Verwaltung von weltweiten CO2-Emissionsdaten.

---

## Voraussetzungen

- Docker Desktop (Windows/macOS) oder Docker Engine (Linux)
- Docker Compose
- Git

---

## Projekt einrichten

1. Repository klonen:
```bash
git clone https://github.com/timxfa98/like-hero-to-zero.git
cd like-hero-to-zero
```

2. Anwendung starten:
```bash
docker compose up -d
```

3. Warten bis die Anwendung bereit ist (ca. 1-2 Minuten)

4. Anwendung öffnen:
- **Web-App**: http://localhost:8080/like-hero-to-zero/
- **WildFly Admin Console**: http://localhost:9990

---

## Container verwalten

```bash
# Starten
docker compose up -d

# Stoppen
docker compose down

# Neu bauen nach Code-Änderungen
docker compose up -d --build

# Logs anzeigen (alle Container)
docker compose logs -f

# Logs anzeigen (nur WildFly)
docker compose logs -f wildfly

# Logs anzeigen (nur MySQL)
docker compose logs -f mysql

# Datenbank zurücksetzen
docker compose down -v
docker compose up -d

# In Container einloggen
docker exec -it herotozero-wildfly bash
docker exec -it herotozero-mysql mysql -uroot -proot herotozero
```

---

## Verwendung

### Als Bürger:in (öffentlich)

1. Öffne http://localhost:8080/like-hero-to-zero/
2. Wähle dein Land aus dem Dropdown
3. Wähle ein Jahr (optional)
4. Klicke "CO2-Daten anzeigen"
5. Die CO2-Emissionsdaten werden angezeigt

### Als Wissenschaftler:in

1. Klicke auf "Anmelden" (oben rechts)
2. Login-Daten eingeben:
   - **Benutzername**: `admin`
   - **Passwort**: `admin`
3. Neue CO2-Daten hinzufügen:
   - Land auswählen
   - Jahr auswählen
   - CO2-Ausstoß in kt eingeben
   - "Daten hinzufügen" klicken
4. Ausstehende Freigaben verwalten:
   - "Freigeben" → Daten werden öffentlich sichtbar
   - "Ablehnen" → Daten werden gelöscht
5. "Abmelden" zum Logout

---

## Projektstruktur

```
like-hero-to-zero/
├── src/main/
│   ├── java/com/herotozero/
│   │   ├── bean/          # CDI Managed Beans
│   │   ├── model/         # JPA Entities
│   │   └── repository/    # Data Access Layer
│   ├── resources/META-INF/
│   │   └── persistence.xml
│   └── webapp/
│       ├── WEB-INF/web.xml
│       ├── index.xhtml    # Öffentliche Ansicht
│       ├── login.xhtml    # Login-Seite
│       └── admin.xhtml    # Admin-Bereich
├── database-setup.sql     # Initiale Daten
├── docker-compose.yml     # Container-Orchestrierung
├── Dockerfile             # WildFly Container
└── startup.sh             # WildFly Konfiguration
```
