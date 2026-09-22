<?php

header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: POST, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");

if ($_SERVER["REQUEST_METHOD"] === "OPTIONS") {
    http_response_code(204);
    exit;
}

require_once "db.php";

if ($_SERVER["REQUEST_METHOD"] !== "POST") {
    http_response_code(405);
    echo json_encode([
        "success" => false,
        "message" => "Only POST method is allowed"
    ]);
    exit;
}

$input = json_decode(file_get_contents("php://input"), true);

$email = isset($input["email"]) ? trim($input["email"]) : "";
$password = isset($input["password"]) ? $input["password"] : "";

if ($email === "" || $password === "") {
    http_response_code(400);
    echo json_encode([
        "success" => false,
        "message" => "Email and password are required"
    ]);
    exit;
}

$sql = "SELECT user_id, name, email, password, phone, address, date_of_birth, role, created_at
        FROM `user`
        WHERE email = ?
        LIMIT 1";

$stmt = $conn->prepare($sql);

if (!$stmt) {
    http_response_code(500);
    echo json_encode([
        "success" => false,
        "message" => "Database query error"
    ]);
    exit;
}

$stmt->bind_param("s", $email);
$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows === 0) {
    http_response_code(401);
    echo json_encode([
        "success" => false,
        "message" => "Invalid email or password"
    ]);
    exit;
}

$user = $result->fetch_assoc();
$storedPassword = $user["password"];

// Supports both password_hash() passwords and the existing demo plain-text passwords.
// Remove the plain-text fallback after all demo passwords are migrated to hashes.
$passwordValid = password_verify($password, $storedPassword);

if (!$passwordValid && hash_equals((string)$storedPassword, (string)$password)) {
    $passwordValid = true;
}

if (!$passwordValid) {
    http_response_code(401);
    echo json_encode([
        "success" => false,
        "message" => "Invalid email or password"
    ]);
    exit;
}

unset($user["password"]);

$stmt->close();
$conn->close();

echo json_encode([
    "success" => true,
    "message" => "Login successful",
    "user" => $user
]);
?>
