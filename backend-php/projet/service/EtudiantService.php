<?php
include_once '../classes/Etudiant.php';
include_once '../connexion/Connexion.php';
include_once '../dao/IDao.php';

class EtudiantService implements IDao {
    private $connexion;

    function __construct() {
        $this->connexion = new Connexion();
    }

    public function create($o) {
        $query = "INSERT INTO Etudiant (nom, prenom, ville, sexe)
                  VALUES (:nom, :prenom, :ville, :sexe)";
        $stmt = $this->connexion->getConnexion()->prepare($query);
        return $stmt->execute([
            ':nom' => $o->getNom(),
            ':prenom' => $o->getPrenom(),
            ':ville' => $o->getVille(),
            ':sexe' => $o->getSexe()
        ]);
    }

    
    public function findAll() {
        $req = $this->connexion->getConnexion()->query("SELECT * FROM Etudiant");
        return $req->fetchAll(PDO::FETCH_OBJ);
    }

    
    public function findById($id) {
        $query = "SELECT * FROM Etudiant WHERE id = :id";
        $stmt = $this->connexion->getConnexion()->prepare($query);
        $stmt->execute([':id' => $id]);
        return $stmt->fetch(PDO::FETCH_OBJ);
    }

    public function delete($id) {
        $query = "DELETE FROM Etudiant WHERE id = :id";
        $stmt = $this->connexion->getConnexion()->prepare($query);
        return $stmt->execute([':id' => $id]);
    }

    public function update($o) {
        $query = "UPDATE Etudiant
                  SET nom = :nom, prenom = :prenom, ville = :ville, sexe = :sexe
                  WHERE id = :id";
        $stmt = $this->connexion->getConnexion()->prepare($query);
        return $stmt->execute([
            ':id' => $o->getId(),
            ':nom' => $o->getNom(),
            ':prenom' => $o->getPrenom(),
            ':ville' => $o->getVille(),
            ':sexe' => $o->getSexe()
        ]);
    }

    // ✅ Si tu veux garder ton API qui renvoie du JSON
    public function findAllApi() {
        $req = $this->connexion->getConnexion()->query("SELECT * FROM Etudiant");
        return $req->fetchAll(PDO::FETCH_ASSOC);
    }
}
?>