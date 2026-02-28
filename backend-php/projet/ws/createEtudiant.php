<?php
include_once '../service/EtudiantService.php';

$data = $_POST;
if (empty($data)) $data = $_GET;   

$nom    = $data['nom'] ?? null;
$prenom = $data['prenom'] ?? null;
$ville  = $data['ville'] ?? null;
$sexe   = $data['sexe'] ?? null;

header('Content-Type: application/json');

if ($nom && $prenom && $ville && $sexe) {
    $es = new EtudiantService();
    $es->create(new Etudiant(null, $nom, $prenom, $ville, $sexe));
    echo json_encode($es->findAllApi());
} else {
    echo json_encode([
        "error" => "Missing parameters",
        "expected" => ["nom","prenom","ville","sexe"]
    ]);
}
?>