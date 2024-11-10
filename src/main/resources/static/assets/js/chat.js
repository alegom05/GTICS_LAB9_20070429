document.addEventListener('DOMContentLoaded', function() {
    console.log('Chat script loaded'); // Para verificar que el script se carga

    const chatButton = document.getElementById('chatButton');
    const chatModal = document.getElementById('chatModal');
    const messageInput = document.getElementById('messageInput');
    const sendMessage = document.getElementById('sendMessage');
    const chatMessages = document.getElementById('chatMessages');

    if (!chatButton || !chatModal || !messageInput || !sendMessage || !chatMessages) {
        console.error('No se encontraron todos los elementos necesarios');
        return;
    }

    // Toggle chat modal
    chatButton.addEventListener('click', function() {
        console.log('Chat button clicked'); // Para debugging
        chatModal.style.display = chatModal.style.display === 'none' ? 'block' : 'none';
    });

    // Send message
    sendMessage.addEventListener('click', sendMessageToBot);

    // Send message on Enter key
    messageInput.addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            sendMessageToBot();
        }
    });

    function sendMessageToBot() {
        const messageText = messageInput.value.trim();
        if (messageText) {
            console.log('Sending message:', messageText); // Para debugging

            // Agregar mensaje del usuario
            addMessage('user', messageText);

            // Preparar el objeto mensaje
            const message = {
                content: messageText,
                timestamp: new Date().toISOString()
            };

            // Llamar a tu API
            fetch('/api/chat/send', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(message)
            })
                .then(response => {
                    console.log('Response received:', response); // Para debugging
                    return response.json();
                })
                .then(data => {
                    console.log('Bot response:', data); // Para debugging
                    addMessage('bot', data.content);
                })
                .catch(error => {
                    console.error('Error:', error);
                    addMessage('bot', 'Lo siento, hubo un error al procesar tu mensaje.');
                });

            messageInput.value = '';
        }
    }

    function addMessage(sender, text) {
        const messageDiv = document.createElement('div');
        messageDiv.className = `message ${sender}-message`;
        messageDiv.textContent = text;
        chatMessages.appendChild(messageDiv);
        chatMessages.scrollTop = chatMessages.scrollHeight;
    }

    console.log('Chat script loaded');
});